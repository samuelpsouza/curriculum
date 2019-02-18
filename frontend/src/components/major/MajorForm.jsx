import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';

export default props => {
    return (
        <Dialog
          open={props.open}
          onClose={props.handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">Novo Curso</DialogTitle>
          <DialogContent>
            <TextField
              autoFocus
              margin="dense"
              id="title"
              label="Título"
              type="text"
              fullWidth
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={props.handleClose} color="primary">
              Cancelar
            </Button>
            <Button onClick={props.handleClose} color="primary">
              Salvar
            </Button>
          </DialogActions>
        </Dialog>
    );
}