import React from "react";
import { Container, Grid2, Typography, TextField, Button } from "@mui/material";
import { Link } from "react-router-dom";
import { signin } from "/Users/isujeong/Desktop/공부/React/TodoProj/TodoFE/todo-react-app/src/service/ApiService.js"


function Login() {
    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.target);
        const username = data.get("username");
        const password = data.get("password");

        // ApiService의 signin 메서드를 사용해 로그인.
        signin({ username: username, password: password });
    };
    return (
        <Container component="main" maxWidth="xs" style={{ marginTop: "8%" }}>
            <Grid2 container spacing={2}>
                <Grid2 item xs={12}>
                    <Typography component="h1" variant="h5">
                    로그인
                    </Typography>
                </Grid2>
            </Grid2>

        <form noValidate onSubmit={handleSubmit}>
        {" "}
        {/* submit 버튼을 누르면 handleSubmit이 실행됨. */}
        <Grid2 container spacing={2}>
            <Grid2 item xs={12}>
                <TextField
                variant="outlined"
                required
                fullWidth
                id="username"
                label="아이디"
                name="username"
                autoComplete="username"
                />
        </Grid2>
        <Grid2 item xs={12}>
            <TextField
            variant="outlined"
            required
            fullWidth
            name="password"
            label="패스워드"
            type="password"
            id="password"
            autoComplete="current-password"
            />
        </Grid2>
        <Grid2 item xs={12}>
            <Button type="submit" fullWidth variant="contained" color="primary">
            로그인
            </Button>
        </Grid2>
        <Grid2 item>
            <Link to="/signup" variant="body2">
            계정이 없습니까? 여기서 가입하세요.
            </Link>
        </Grid2>
    </Grid2>
    </form>
    </Container>
);
};
export default Login;
