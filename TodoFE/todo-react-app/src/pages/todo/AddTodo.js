import React, {useState} from "react";
import { Button, Grid, Grid2, TextField } from "@mui/material";

const AddTodo = (props) => {
    const [item,setItem] = useState({title : ""});
    const addItem = props.addItem;
    
    const onButtonClick = () => {
        addItem(item)
        setItem({title : ""})
    }

    const enterEventHandler = (e) => {
        if(e.key == 'Enter') {
            onButtonClick()
        }
    }

    const onInputChange = (e) => {
        setItem({title:e.target.value})
    }

    return (
        <Grid2 container style={{marginTod:20}}>
            <Grid2 xs={11} md={11} item style={{ paddingRight: 16 }}>
                <TextField placeholder="Add Todo here" onChange={onInputChange} value={item.title} onKeyDown={enterEventHandler}/>
            </Grid2>
            <Grid2 xs={1} md={1} item>
                <Button style={{height: '100%'}} color="secondary" variant="outlined" onClick={onButtonClick}>
                    +
                </Button>
            </Grid2>
        </Grid2>
    )
}

export default AddTodo 