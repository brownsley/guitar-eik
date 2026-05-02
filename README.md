# 🎸 Guitar Eik

A clean, ad-free space for guitarists to practice and perform.

---

## 💡 Why I built this
I struggled to find a reliable guitar chord app without intrusive ads. So, I decided to build my own using **Spring Boot**.

## ✨ Key Features
* **Ad-Free:** Zero distractions.
* **Custom Library:** Add and save your favorite songs.
* **Simple:** Focus on chords and lyrics only.

## 🛠 Tech Stack
* **Backend:** Spring Boot (Java)
* **Build Tool:** Maven
* **CI/CD:** GitHub Actions
* **Deployment:** VPS

## 🚀 Automation & Deployment
This project uses **GitHub Actions** for automated builds and testing. Every push to `main` deploys directly to my **VPS**, ensuring the live version is always up to date.

## 💻 Getting Started
Clone and run locally:

```bash
git clone [https://github.com/brownsley/guitar-eik.git](https://github.com/brownsley/guitar-eik.git)
cd guitar-eik
./mvnw spring-boot:run