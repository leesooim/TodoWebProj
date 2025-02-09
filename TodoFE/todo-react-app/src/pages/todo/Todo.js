import React, { useState } from "react";
import { ListItem, ListItemText, InputBase, Checkbox,IconButton} from "@mui/material";
import DeleteOutlined from "@mui/icons-material/DeleteOutlined";


const Todo = (props) => {
    const [item, setItem] = useState(props.item);
    const deleteItem = props.deleteItem

    const deleteEnventHandler = () => {
        deleteItem(item)
    }

    return(
       <ListItem>
            <Checkbox checked={item.done}/>
            <ListItemText>
                <InputBase
                 inputProps={{ "aria-label": "naked"}}
                 type="text"
                 id={item.id}
                 name={item.id}
                 value={item.title}
                 multiline={true}
                 fullWidth={true}
                />
            </ListItemText>
            
                <IconButton aria-label="Delete Todo" onClick={deleteEnventHandler}>
                    <DeleteOutlined/>
                </IconButton>
            
       </ListItem>
    )
}

export default Todo;