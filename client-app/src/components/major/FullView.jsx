import React, {Fragment} from 'react';
import { Divider, Button, Dialog, DialogActions, 
  DialogContent, DialogTitle, Typography, List,
  ListItem, ListItemText } from '@material-ui/core';


export default props => {
    let optionals = props.major.matrix.courseList.filter(course => course.semester === null || course.semester !== undefined);
    let mandatory = props.major.matrix.courseList.filter(course => course.semester !== null && course.semester !== undefined);
    
    return (
        <Dialog
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
              
              {props.major.matrix.semesterList.map(semester => {
                return (
                  <Fragment key={semester.id}>
                    <Typography>{semester.description}</Typography>
                    <List>
                      {mandatory.map(course => (
                        <ListItem key={course.id} dense button>
                          <ListItemText primary={course.id} />
                          <ListItemText primary={course.description} />
                      </ListItem>
                      ))}
                    </List>
                  </Fragment>
                )
              })}

              <Divider />
              <Typography variant="h6" color="inherit">
                Disciplinas Optativas
              </Typography>

              <List>
                {optionals.map(course => (
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