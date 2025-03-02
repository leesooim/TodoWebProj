import React from "react";
import "../index.css";
import App from "../App.js";
import Login from '../pages/login/Login.js'
import SignUp from '../pages/login/SignUp.js';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Typography, Box } from "@mui/material";



function Copyright() {
    return(
        <Typography variant="body2" color="textSecondary" >
            {"Copyright © "}
            fsoftwareengineer, {new Date().getFullYear()}
            {"."}
        </Typography>
    )
}

function AppRouter() {
    console.log("✅ AppRouter.js 실행됨!");
    return (
    <div>   
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<App />} />
                <Route path="/login" element={<Login />} />
                <Route path="signup" element={<SignUp />} />
            </Routes>
        </BrowserRouter>
        <Box mt={5}>
        <Copyright />
        </Box>
    </div>
    );
};

export default AppRouter;