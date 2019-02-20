import React from 'react';
import { Dialog, DialogTitle, DialogContent,
    DialogActions, Button, TextField } from '@material-ui/core';

export default props => {
    return (
        <Dialog
            open={this.state.openSemesterSelection}
            aria-labelledby="form-dialog-title"
            >  
            <DialogTitle id="form-dialog-title">Login</DialogTitle>
            <DialogContent>
                <TextField margin="dense" id="username" label="Usuário" type="text" fullWidth/>
                <TextField margin="dense" id="password" label="Senha" type="password" fullWidth/>
            </DialogContent>
            <DialogActions>
                <Button color="primary">
                    Cancelar
                </Button>
                <Button color="primary">
                    Login
                </Button>
            </DialogActions>
        </Dialog>
    );
}