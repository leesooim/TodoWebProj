// import logo from './logo.svg';
import React, { useState } from 'react';
import './App.css';
import  Todo from './pages/todo/Todo'
import AddTodo from './pages/todo/AddTodo';
import { Container, List, Paper } from "@mui/material";

function App() {
  const [items, setItems] = useState([]);

const addItem = (item) => {
  item.id = "ID-" + items.length; 
  item.done = false;
  
  // 업데이트는 반드시 setItems로 하고 새 배열을 만들어야 한다.
  setItems([...items, item]);
  console.log("items : ", items);
  };

  const deleteItem = (item) => {
    const newItem = items.filter(e => e.id != item.id)
    setItems([...newItem])
  }

  let todoItems = items.length > 0 && (
    <Paper style={{margin:16}}>
      <List>
        {items.map((item) =>(
          <Todo item={item} key={item.id} deleteItem={deleteItem}/>
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
