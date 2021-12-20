import React from "react";
import { Link } from "react-router-dom";
import { Button } from "antd";
import "bootstrap/dist/css/bootstrap.min.css";
import "antd/dist/antd.css";

const LandingPage = () => {
  return (
    <div className="container">
      <div
        className="mx-auto"
        style={{
          width: 600,
          margin: "auto",
          padding: 30,
          justifyContent: "center",
        }}
      >
        <h1>Welcome to NBAStats.com</h1>
        <div className="mx-auto">
          <Link to="/selector">
            <Button type="primary" htmlType="submit">
              Get Started
            </Button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default LandingPage;
