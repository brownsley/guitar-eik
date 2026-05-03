# Project Name : Guitar Eik


## Why I Built This

I love playing the guitar, but I had a hard time using other apps. Many apps had too many ads, or they did not have the songs I wanted to play.

I decided to create my own app to solve this. Now, I have a simple place where I can save all my favorite songs and practice whenever I want. This app helps me focus on playing music instead of searching for chords.

## 🛠 Tech Stack
* **Backend:** Spring Boot (Java)
* **Build Tool:** Maven
* **CI/CD:** GitHub Actions
* **Deployment:** VPS

## Automation & Deployment
This project uses **GitHub Actions** for automated builds and testing. Every push to `main` deploys directly to my **VPS**, ensuring the live version is always up to date.

## Getting Started
Clone and run locally:

```bash
git clone [https://github.com/brownsley/guitar-eik.git](https://github.com/brownsley/guitar-eik.git)
cd guitar-eik
./mvnw spring-boot:run