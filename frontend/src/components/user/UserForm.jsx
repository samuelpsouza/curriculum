import React from 'react';
import { Button, TextField, Dialog,
  DialogActions, DialogContent, DialogTitle, MenuItem } from '@material-ui/core';

const UserForm = props => {
    return (
        <Dialog
          open={props.open}
          onClose={props.handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">Novo Usuario</DialogTitle>
          <DialogContent>
            <TextField autoFocus margin="dense" id="username" label="Usuário" type="text" value={props.code} onChange={props.handleChange('username')} fullWidth/>
            <TextField margin="dense" id="password" label="Senha" type="password" value={props.title} onChange={props.handleChange('password')} fullWidth/>
            <TextField margin="dense" id="roles" select label="Permissões" value={props.period} onChange={props.handleChange('roles')} fullWidth>
                {props.roles.map(role => (
                    <MenuItem key={role.id} value={role.name}>
                        {role.name}
                    </MenuItem>
                ))}
            </TextField>
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

export default UserForm;