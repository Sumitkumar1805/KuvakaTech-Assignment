# 🚀 KuvakaTech Backend Assignment

**Author:** Sumit Kumar  
**Role:** Backend Engineer Assignment Submission  
**Tech Stack:** Spring Boot (Java 17), OpenAI API, Maven, Docker  

---

## 🎯 Objective

This backend service evaluates the **buying intent** of uploaded leads for a given product or offer.  
It combines **rule-based scoring (50 points)** and **AI-based reasoning (50 points)** to classify each lead as **High**, **Medium**, or **Low intent**.

---

## ⚙️ Features

✅ REST APIs for:
- `POST /offer` — create a new offer  
- `POST /leads/upload` — upload CSV of leads  
- `POST /score` — process and score uploaded leads  
- `GET /results` — view scored leads as JSON  
- `GET /results/export` — export results as CSV  

✅ Scoring Logic:
- **Rule Layer (0–50 pts)**
  - Role relevance: Decision maker (+20), Influencer (+10)
  - Industry match: Exact ICP (+20), Adjacent (+10)
  - Data completeness: All fields present (+10)
- **AI Layer (0–50 pts)**
  - Calls OpenAI/Gemini API with offer + lead details
  - Asks model to classify intent (High/Medium/Low) and explain briefly
  - Maps `High=50`, `Medium=30`, `Low=10`
- **Final Score:** `rule_score + ai_points`

✅ Dockerized for easy deployment  
✅ Unit tests for `ScoringService`  
✅ Optional CSV export endpoint  

---

## 🧠 Architecture Overview
