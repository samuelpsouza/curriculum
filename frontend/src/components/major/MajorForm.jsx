import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import MenuItem from '@material-ui/core/MenuItem';


const periods = [
    {
      value: 'MANHA',
      label: 'Manhã',
    },
    {
      value: 'TARDE',
      label: 'Tarde',
    },
    {
      value: 'NOITE',
      label: 'Noite',
    }
  ];

export default props => {
    let period = '';
    
    return (
        <Dialog
          open={props.open}
          onClose={props.handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">Novo Curso</DialogTitle>
          <DialogContent>
            <TextField autoFocus margin="dense" id="code" label="Código" type="text" fullWidth/>
            <TextField margin="dense" id="title" label="Título" type="text" fullWidth/>
            <TextField margin="dense" id="description" label="Descrição" type="textarea" fullWidth/>
            <TextField margin="dense" id="period" select label="Período" value={period} fullWidth>
                {periods.map(option => (
                    <MenuItem key={option.value} value={option.value}>
                        {option.label}
                    </MenuItem>
                ))}
            </TextField>
            <TextField margin="dense" id="duration" label="Duração" type="text" fullWidth/>
            <TextField margin="dense" id="registrationNumber" label="Vagas Disponíveis" type="number" fullWidth/>
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