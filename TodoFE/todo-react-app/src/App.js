// import logo from './logo.svg';
import React, { useEffect, useState } from 'react';
import './App.css';
import  Todo from './pages/todo/Todo'
import AddTodo from './pages/todo/AddTodo';
import { Container, List, Paper } from "@mui/material";
import { call } from "./service/ApiService";

function App() {
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
  return (
    <div className="App">
      <Container maxWidth="md">
        <AddTodo addItem={addItem} />
          <div className='TodoList'>
          {todoItems}
      </  div>
      </Container>
    </div>
  );
}

export default App;
