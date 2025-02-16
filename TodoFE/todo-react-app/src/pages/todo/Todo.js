import React, { useState } from "react";
import { ListItem, ListItemText, InputBase, Checkbox,IconButton} from "@mui/material";
import DeleteOutlined from "@mui/icons-material/DeleteOutlined";


const Todo = (props) => {
    const [item, setItem] = useState(props.item);
    const [readOnly, setReadOnly] = useState(true);
    const deleteItem = props.deleteItem
    const editItem = props.editItem

    const deleteEnventHandler = () => {
        deleteItem(item)
    }

    const editEventHandler = (e) => {
        setItem({...item, title: e.target.value});
    }

    const checkboxEventHandler = (e) => {
        item.done = e.target.checked;
        editItem(item)
      }

    const turnOffReadOnly = () => {
        setReadOnly(false)
    }

    const trunOnReadonly = (e) => {
        if(e.key === 'Enter') {
            setReadOnly(true)
            editItem(item);
        }
    }

    return(
       <ListItem>
            <Checkbox checked={item.done} onChange={checkboxEventHandler}/>
            <ListItemText>
                <InputBase
                 inputProps={{ "aria-label": "naked", readOnly: readOnly}}
                 onClick={turnOffReadOnly}
                 onKeyDown={trunOnReadonly}
                 onChange={editEventHandler}
                 type="text"
                 id={item.id}
                 name={item.id}
                 value={item.title}
                 multiline={true}
                 fullWidth
                />
            </ListItemText>
            
                <IconButton aria-label="Delete Todo" onClick={deleteEnventHandler}>
                    <DeleteOutlined/>
                </IconButton>
            
       </ListItem>
    )
}

export default Todo;