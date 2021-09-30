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
          <DialogTitle id="form-dialog-title">Nova Disciplina</DialogTitle>
          <DialogContent>
            <TextField autoFocus margin="dense" id="code" label="Código" type="text" value={props.code} onChange={props.handleChange('code')} fullWidth/>
            <TextField margin="dense" id="description" label="Descrição" type="textarea" value={props.description} onChange={props.handleChange('description')} fullWidth/>
          </DialogContent>
          <DialogActions>
            <Button onClick={props.handleClose} color="primary">
              Cancelar
            </Button>
            <Button onClick={props.handleSubmit} color="primary">
              Salvar
            </Button>
          </DialogActions>
        </Dialog>
    );
}