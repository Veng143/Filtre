# 🧪 Filtre — A Modular Java File Filter Framework

Welcome to **Filtre**, a clean and flexible Java project for applying customizable filters to both **text and binary files**.  
Built with **modularity in mind**, this project gives you the power to transform files using pluggable filter classes — from simple case swaps to cryptographic transformations.  

---

## 🚀 Features

- 📁 Supports both **text** and **binary** files
- 🧩 Easy-to-add **custom filters** (e.g., Atbash, bitwise NOT, Caesar-like encryption)
- 🔧 Configuration via `config.json` for filter parameters
- 📦 Organized into multiple packages (`filtres`, `config`, `gestionnaire`)
- 🛠️ Built with clean Java code and scalable structure

---

## 🧰 Tech Stack

- Java 17+  
- GSON for JSON parsing  
- CLI-based user interaction  
- No external frameworks — just pure, powerful Java

---

## 🧪 How It Works

1. 🗂 User selects a file (text or binary)
2. 🔍 Filters compatible with that file type are shown
3. ⚙️ Filter is applied using parameters defined in `config.json`
4. 💾 Modified content is saved to output

---

## 📦 Project Structure

