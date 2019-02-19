import React from 'react';
import { Divider, Button, Dialog, DialogActions, 
  DialogContent, DialogTitle, Typography, List,
  ListItem, ListItemText } from '@material-ui/core';


export default props => {
    return (
        <Dialog
          fullScreen
          open={props.open}
          onClose={props.handleClose}
          aria-labelledby="responsive-dialog-title"
        >
          <DialogTitle id="responsive-dialog-title">{props.major.title}</DialogTitle>
          <DialogContent>
              Periodo: {props.major.period} <br />
              Duração: {props.major.duration}

              <Typography variant="h6" color="inherit">
                Disciplinas Obrigatórias
              </Typography>
              <Divider />
              <Typography variant="h6" color="inherit">
                Disciplinas Optativas
              </Typography>

              <List>
                {props.major.matrix.courseList.map(course => (
                    <ListItem key={course.id} dense button>
                      <ListItemText primary={course.id} />
                      <ListItemText primary={course.description} />
                  </ListItem>
                  ))}
              </List>
          </DialogContent>
          <DialogActions>
            <Button color="primary" onClick={props.handleClose}>
              Fechar
            </Button>
          </DialogActions>
        </Dialog>
    );
}