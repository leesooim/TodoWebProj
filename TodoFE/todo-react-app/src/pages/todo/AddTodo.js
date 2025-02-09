import React, {useState} from "react";
import { Button, Grid2, TextField } from "@mui/material";

const AddTodo = (props) => {
    const [item,setItem] = useState({title : ""});
    const [isComposing, setIsComposing] = useState(false); // 한글입력시 마지막글자가 한번 더 저장되는것을 방지하기 위하여 추가
    const addItem = props.addItem;
    
    const onButtonClick = () => {
        if (!item.title.trim()) return;
        addItem(item)
        setItem({title : ""})
    }

    const enterEventHandler = (e) => {
        if(e.key === 'Enter'  && !isComposing) {
            e.preventDefault(); 
            e.stopPropagation();
            onButtonClick()
        }
    }

    const onInputChange = (e) => {
        setItem({title:e.target.value})
    }

    return (
        <Grid2 container style={{marginTod:20}}>
            <Grid2 xs={11} md={11} style={{ paddingRight: 16 }}>
                <TextField fullWidth 
                    placeholder="Add Todo here" 
                    onChange={onInputChange} 
                    value={item.title} 
                    onKeyDown={enterEventHandler} 
                    onCompositionStart={() => setIsComposing(true)}
                    onCompositionEnd={() => setIsComposing(false)}/>
            </Grid2>
            <Grid2 xs={1} md={1} >
                <Button style={{height: '100%'}} color="secondary" variant="outlined" onClick={onButtonClick}>
                    +
                </Button>
            </Grid2>
        </Grid2>
    )
}

export default AddTodo 