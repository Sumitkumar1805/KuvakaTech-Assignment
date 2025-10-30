package com.sumit.Kuvaka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
                <html>
                    <head>
                        <title>KuvakaTech Assignment – API Endpoints</title>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                background-color: #f8f9fa;
                                color: #333;
                                padding: 40px;
                            }
                            h1 {
                                color: #007bff;
                            }
                            ul {
                                list-style-type: none;
                                padding-left: 0;
                            }
                            li {
                                background: #fff;
                                margin: 10px 0;
                                padding: 15px;
                                border-radius: 8px;
                                box-shadow: 0 2px 6px rgba(0,0,0,0.1);
                            }
                            a {
                                color: #007bff;
                                text-decoration: none;
                            }
                            a:hover {
                                text-decoration: underline;
                            }
                        </style>
                    </head>
                    <body>
                        <h1>KuvakaTech Assignment – API Endpoints</h1>
                        <p>Welcome to the backend service. Below are available endpoints:</p>
                        <ul>
                            <li><strong>GET</strong> → <a href="/api/users">/api/users</a> — Get all users</li>
                            <li><strong>POST</strong> → <code>/api/users</code> — Add a new user</li>
                            <li><strong>GET</strong> → <a href="/api/users/{id}">/api/users/{id}</a> — Get user by ID</li>
                            <li><strong>PUT</strong> → <code>/api/users/{id}</code> — Update user</li>
                            <li><strong>DELETE</strong> → <code>/api/users/{id}</code> — Delete user</li>
                        </ul>
                        <p><em>Deployed on Render by Sumit Kumar.</em></p>
                    </body>
                </html>
                """;
    }
}
