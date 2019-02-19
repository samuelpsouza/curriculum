import React from 'react';
import { Button, TextField, Dialog,
  DialogActions, DialogContent, DialogTitle, MenuItem } from '@material-ui/core';


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
    return (
        <Dialog
          open={props.open}
          onClose={props.handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">Novo Curso</DialogTitle>
          <DialogContent>
            <TextField autoFocus margin="dense" id="code" label="Código" type="text" value={props.code} onChange={props.handleChange('code')} fullWidth/>
            <TextField margin="dense" id="title" label="Título" type="text" value={props.title} onChange={props.handleChange('title')} fullWidth/>
            <TextField margin="dense" id="description" label="Descrição" type="textarea" value={props.description} onChange={props.handleChange('description')} fullWidth/>
            <TextField margin="dense" id="period" select label="Período" value={props.period} onChange={props.handleChange('period')} fullWidth>
                {periods.map(option => (
                    <MenuItem key={option.value} value={option.value}>
                        {option.label}
                    </MenuItem>
                ))}
            </TextField>
            <TextField margin="dense" id="duration" label="Duração" type="text" value={props.duration} onChange={props.handleChange('duration')} fullWidth/>
            <TextField margin="dense" id="registrationNumber" label="Vagas Disponíveis" type="number" value={props.registrationNumber} onChange={props.handleChange('registrationNumber')} fullWidth/>
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