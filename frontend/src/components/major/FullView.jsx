import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';

export default props => {
    return (
        <Dialog
          fullScreen={props.fullScreen}
          open={props.open}
          onClose={props.handleClose}
          aria-labelledby="responsive-dialog-title"
        >
          <DialogTitle id="responsive-dialog-title">{props.major.title}</DialogTitle>
          <DialogContent>
              Periodo: {props.major.period} <br />
              Duração: {props.major.duration}
          </DialogContent>
          <DialogActions>
            <Button color="primary" onClick={props.handleClose}>
              Fechar
            </Button>
          </DialogActions>
        </Dialog>
    );
}