package com.sumit.Kuvaka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>KuvakaTech Assignment — API Endpoints</title>
            <style>
                body {
                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                    background: linear-gradient(135deg, #f0f4ff, #ffffff);
                    margin: 0;
                    padding: 0;
                    color: #333;
                }

                header {
                    background: #003366;
                    color: #fff;
                    text-align: center;
                    padding: 25px 10px;
                    box-shadow: 0 4px 12px rgba(0,0,0,0.2);
                }

                header h1 {
                    margin: 0;
                    font-size: 28px;
                    letter-spacing: 0.5px;
                }

                .container {
                    max-width: 900px;
                    margin: 30px auto;
                    padding: 20px;
                }

                .endpoint {
                    background: #fff;
                    border-radius: 12px;
                    padding: 20px;
                    margin-bottom: 20px;
                    box-shadow: 0 4px 10px rgba(0,0,0,0.1);
                    transition: transform 0.2s ease, box-shadow 0.2s ease;
                }

                .endpoint:hover {
                    transform: translateY(-4px);
                    box-shadow: 0 8px 20px rgba(0,0,0,0.12);
                }

                .method {
                    font-weight: bold;
                    padding: 6px 10px;
                    border-radius: 6px;
                    color: #fff;
                    margin-right: 8px;
                }

                .get { background-color: #28a745; }
                .post { background-color: #007bff; }

                code {
                    background: #f3f3f3;
                    padding: 3px 6px;
                    border-radius: 4px;
                    color: #d63384;
                }

                pre {
                    background: #f9f9f9;
                    padding: 12px;
                    border-radius: 8px;
                    overflow-x: auto;
                    font-size: 14px;
                    color: #444;
                }

                footer {
                    text-align: center;
                    margin-top: 40px;
                    padding: 20px 0;
                    color: #777;
                    font-size: 14px;
                }

                a {
                    color: #003366;
                    text-decoration: none;
                }

                a:hover {
                    text-decoration: underline;
                }
            </style>
        </head>
        <body>
            <header>
                <h1>KuvakaTech Assignment — API Endpoints</h1>
            </header>

            <div class="container">

                <div class="endpoint">
                    <div><span class="method post">POST</span> <code>/offer</code></div>
                    <p>Create a new product/offer.</p>
                    <pre>{
  "name": "AI Outreach Automation",
  "valueProps": ["24/7 outreach", "6x more meetings"],
  "idealUseCases": ["B2B SaaS mid-market"],
  "icpIndustries": ["SaaS"],
  "adjacentIndustries": ["MarTech"]
}</pre>
                </div>

                <div class="endpoint">
                    <div><span class="method post">POST</span> <code>/leads/upload?offerId=&lt;offerId&gt;</code></div>
                    <p>Upload a CSV of leads (columns: name, role, company, industry, location, linkedin_bio).</p>
                    <pre>curl -X POST "https://kuvakatech-assignment-klm5.onrender.com/leads/upload?offerId=YOUR_ID" \\
-F "file=@leads.csv"</pre>
                </div>

                <div class="endpoint">
                    <div><span class="method post">POST</span> <code>/score</code></div>
                    <p>Run the scoring pipeline for a given offer.</p>
                    <pre>{
  "offerId": "YOUR_ID"
}</pre>
                </div>

                <div class="endpoint">
                    <div><span class="method get">GET</span> <code>/results?offerId=&lt;offerId&gt;</code></div>
                    <p>Fetch all scored leads (name, role, company, intent, score, reasoning).</p>
                </div>

                <div class="endpoint">
                    <div><span class="method get">GET</span> <code>/results/export?offerId=&lt;offerId&gt;</code></div>
                    <p>Download the results as a CSV file.</p>
                </div>

            </div>

            <footer>
                <p>Deployed by <strong>Sumit Kumar</strong> | 
                <a href="https://kuvakatech-assignment-klm5.onrender.com">Live API</a></p>
            </footer>
        </body>
        </html>
        """;
    }
}