# Coinbase Build Runbook

This runbook describes the build and deployment pipeline for the Coinbase project. The pipeline is triggered on pushes and pull requests to the `master` branch.

## Workflow Triggers

- **Push to master**
- **Pull request to master**

## Job: Build and Deploy

### 1. Checkout Latest Code
- **Step:** `actions/checkout@v4`
- **Description:** The latest code from the `master` branch is checked out.

### 2. Set Up JDK 21
- **Step Name:** `Setting up JDK 21`
- **Action:** `actions/setup-java@v4`
- **Description:** JDK 21 is installed using the Temurin distribution, and Maven caching is enabled for efficiency.

### 3. Build with Maven
- **Step Name:** `Build with Maven`
- **Description:** Runs `mvn clean install` to build the project.

### 4. Set Up AWS ECR Credentials
- **Step Name:** `Setting up AWS ECR Details`
- **Action:** `aws-actions/configure-aws-credentials@v4`
- **Description:** AWS credentials are configured using secrets for access to AWS services.

### 5. Login to Amazon ECR
- **Step Name:** `Login to Amazon ECR`
- **Action:** `aws-actions/amazon-ecr-login@v2`
- **Description:** Logs into Amazon ECR, and outputs the ECR registry URL for further use.

### 6. Build and Push Docker Image
- **Step Name:** `Build and push Docker image`
- **Description:** 
  - Builds a Docker image using the latest code.
  - The image is tagged with `coinbase-latest` and pushed to the Amazon ECR repository.

### 7. Set Up Kubernetes CLI
- **Step Name:** `Set up Kubernetes CLI`
- **Action:** `azure/setup-kubectl@v4`
- **Description:** Installs `kubectl` version 1.31.0 for interacting with Kubernetes clusters.

### 8. Configure kubectl
- **Step Name:** `Configure kubectl`
- **Description:**
  - Sets up `kubectl` using the provided Kubeconfig.
  - Decodes the base64-encoded Kubeconfig and saves it to the default location.

### 9. Deploy to Kubernetes
- **Step Name:** `Deploy to Kubernetes`
- **Description:**
  - Applies the Kubernetes deployment and service manifests.
  - Restarts the `coinbase-deployment-blue` and `coinbase-deployment-green` deployments for a blue-green deployment strategy.

### 10. Install Newman
- **Step Name:** `Install Newman`
- **Description:** Installs Newman, a CLI for running Postman collections, via npm.

### 11. Run Postman Collection
- **Step Name:** `Run Postman Collection`
- **Description:** 
  - Executes a Postman collection using Newman.
  - The Postman collection URL is accessed using the `POSTMAN_API_KEY` from secrets.

## Notes
- Ensure all necessary secrets (AWS credentials, ECR repository, Postman API key, Kubeconfig) are available in your GitHub repository.
- This pipeline uses blue-green deployment to minimize downtime during deployments.


Damian Peiris ©️ All rights reserved 2024.

