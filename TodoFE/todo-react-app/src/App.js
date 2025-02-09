// import logo from './logo.svg';
import React, { useState } from 'react';
import './App.css';
import  Todo from './pages/todo/Todo'
import { List, Paper } from "@mui/material";

function App() {
  const [item,setItem] = useState([
    {
    id:"0",
    title:"Hello World",
    done:true
  },
  {
    id:"1",
    title:"Hello World2",
    done:false
  }
])

  let todoItems = item.length > 0 && (
    <Paper style={{margin:16}}>
      <List>
        {item.map((item) =>(
          <Todo item={item} key={item.id}/>
         ))}
      </List>
    </Paper>
  )
  return (
    <div className="App">
      {todoItems}
    </div>
  );
}

export default App;
