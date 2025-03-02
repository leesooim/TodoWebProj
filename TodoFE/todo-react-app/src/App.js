// import logo from './logo.svg';
import React, { useEffect, useState } from 'react';
import './App.css';
import  Todo from './pages/todo/Todo'
import AddTodo from './pages/todo/AddTodo';
import { 
  Container,
  List,
  Paper,
  Grid2,
  Button,
  AppBar,
  Toolbar,
  Typography,
} from "@mui/material";
import { call, signout } from "./service/ApiService";

function App() {
  console.log("app.js 실행")
  const [items, setItems] = useState([]);

  useEffect(() => {
    call("/todo", "GET", null)
    .then((response) => setItems(response.data));
  },[])
  

  const addItem = (item) => {
    call("/todo", "POST", item)
    .then((response) => setItems(response.data));

    console.log("items : ", items);
    };

  const deleteItem = (item) => {
    call("/todo", "DELETE", item)
    .then((response) => setItems(response.data));
  }

  const editItem = (item) => {
    call("/todo","PUT", item)
    .then((respons) => setItems(respons.data))
  }

  let todoItems = items.length > 0 && (
    <Paper style={{margin:16}}>
      <List>
        {items.map((item) =>(
          <Todo item={item} key={item.id} editItem={editItem} deleteItem={deleteItem}/>
         ))}
      </List>
    </Paper>
  )

  let navigationBar = (
    <AppBar position="static">
      <Toolbar>
        <Grid2 justifyContent="space-between" container>
          <Grid2 item>
            <Typography variant="h6">오늘의 할일</Typography>
          </Grid2>
        <Grid2 item>
          <Button color="inherit" raised onClick={signout}>
          로그아웃
          </Button>
        </Grid2>
      </Grid2>
    </Toolbar>
  </AppBar>
  );

  return (
    <div className="App">
      {navigationBar} {/* 네비게이션 바 렌더링 */}
      <Container maxWidth="md">
        <AddTodo addItem={addItem} />
        <div className="TodoList">{todoItems}</div>
      </Container>
    </div>
  );

}



export default App;
