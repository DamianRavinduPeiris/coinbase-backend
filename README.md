# Runbook for `coinbase-build` Workflow.

## Triggering Events.
- The workflow is triggered on:
  - **Push** to the `master` branch.
  - **Pull Request** to the `master` branch.

## Job: `build`

### Step 1: Checkout the Latest Code.
- **Action**: `actions/checkout@v4`
- **Description**: Checks out the latest code from the repository.
- **Troubleshooting**: 
  - Ensure that the repository has the correct permissions for the GitHub Actions to pull the code.

### Step 2: Set up JDK 21.
- **Action**: `actions/setup-java@v4`
- **Description**: Sets up JDK 21 (Temurin distribution) and configures Maven caching.
- **Troubleshooting**: 
  - If JDK installation fails, verify the specified Java version (`21`) and ensure Temurin is the correct distribution.

### Step 3: Build with Maven.
- **Command**: `mvn clean install`
- **Description**: Builds the project using Maven.
- **Troubleshooting**: 
  - Check Maven logs for dependency, compilation, or test failures.
  - Ensure all required dependencies are defined in the `pom.xml`.

### Step 4: Set up AWS ECR Details.
- **Action**: `aws-actions/configure-aws-credentials@v4`
- **Description**: Configures AWS credentials for access to ECR.
- **Troubleshooting**: 
  - Check that `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, and `AWS_REGION` are correctly set in GitHub Secrets.

### Step 5: Login to Amazon ECR.
- **Action**: `aws-actions/amazon-ecr-login@v2`
- **Description**: Logs in to Amazon ECR to push Docker images.
- **Troubleshooting**: 
  - Verify that the `ECR` permissions are set up correctly in AWS.

### Step 6: Build and Push Docker Image.
- **Command**: 
  ```bash
  docker build -t $ECR_REGISTRY/$ECR_REPOSITORY:coinbase-$IMAGE_TAG .
  docker push $ECR_REGISTRY/$ECR_REPOSITORY:coinbase-$IMAGE_TAG
  ```
- **Description**: Builds the Docker image and pushes it to the ECR repository.
- **Troubleshooting**: 
  - If the image build fails, check the Dockerfile for issues.
  - If the push fails, ensure that the `ECR_REPOSITORY` secret is correctly set.

### Step 7: Set up Kubernetes CLI (kubectl).
- **Action**: `azure/setup-kubectl@v4`
- **Description**: Installs the `kubectl` CLI.
- **Troubleshooting**: 
  - If installation fails, check the specified version (`v1.31.0`) and the correctness of the version number.

### Step 8: Configure `kubectl` with Kubeconfig.
- **Environment Variable**: `KUBECONFIG`
- **Description**: Configures `kubectl` using the Kubernetes configuration stored in GitHub Secrets.
- **Troubleshooting**: 
  - Ensure the `KUBECONFIG` value in GitHub Secrets is correct.
  - If `kubectl` fails to authenticate, verify the contents of the kubeconfig.

### Step 9: Apply Kubernetes Manifests.
- **Command**: 
  ```bash
  kubectl apply -f k8s/deployment.yaml
  kubectl apply -f k8s/service.yaml
  ```
- **Description**: Deploys the application using Kubernetes manifests.
- **Troubleshooting**: 
  - If deployment fails, check the manifest files for syntax errors.
  - Ensure the Kubernetes cluster is available and accessible.

### Step 10: Install Newman.
- **Command**: `npm install -g newman`
- **Description**: Installs Newman globally, used to run Postman collections.
- **Troubleshooting**: 
  - Ensure `npm` is installed and available.
  - If installation fails, check for network or permission issues.

### Step 11: Run Postman Collection with Newman.
- **Command**: 
  ```bash
  newman run https://api.postman.com/collections/27406561-af7f744d-2fad-475d-8cd9-27fa633d78db?access_key=$POSTMAN_API_KEY
  ```
- **Description**: Runs the Postman collection using the Newman CLI, with the Postman API key retrieved from GitHub Secrets.
- **Troubleshooting**: 
  - Ensure that the `POSTMAN_API_KEY` is set correctly in GitHub Secrets.
  - If the collection run fails, verify the collection URL and API key.


2024 ©️ Damian Peiris All Rights Reserved.
