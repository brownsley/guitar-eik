Guitar Eik
I love playing guitar, but I couldn't find a chord app I liked. The other apps had too many ads and didn't have the songs I wanted to play. So, I decided to build my own using Spring Boot!

Why I made this
No Ads: I wanted a clean space to play music without interruptions.

My Favorite Songs: I can add and save all the songs I love.

Simple: Just the chords and lyrics. No extra mess.

Tech Stack
Backend: Spring Boot (Java)

Build Tool: Maven

CI/CD: GitHub Actions

Deployment: VPS (Virtual Private Server)

🚀 Deployment & Automation
This project uses GitHub Actions for CI/CD. Every time I push code to the main branch, the project automatically builds, tests, and deploys to my VPS, ensuring the live version is always up to date.

How to run locally
If you want to run this on your own computer:

Clone the project:

Bash
git clone https://github.com/brownsley/guitar-eik.git
Move to the project folder:

Bash
cd guitar-eik
Start the app:

Bash
./mvnw spring-boot:run
Built by Brownsley for my own guitar sessions.

Why this is great:
CI/CD: Mentioning GitHub Actions tells anyone reading that you know how to automate your workflow.

Deployment: Mentioning the VPS shows you know how to take an app from "my computer" to "the real internet."