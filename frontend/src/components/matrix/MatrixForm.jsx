import React, { Component } from 'react';
import { Button, TextField, Dialog,
    DialogActions, DialogContent, DialogTitle, MenuItem,
    Typography, Divider } from '@material-ui/core';

class MatrixForm extends Component {
    render(){
        const {openInclude, handleClose, handleClose, handleSubmit} = this.props;
        return (
            <Dialog
                open={openInclude}
                onClose={handleClose}
                aria-labelledby="form-dialog-title"
            >
                <DialogTitle id="form-dialog-title">Novo Curso</DialogTitle>
                <DialogContent>
                <Typography>Disciplinas Obrigatórioas</Typography>
                <Divider />
                <Typography>Disciplinas Optativas</Typography>
                </DialogContent>
                <DialogActions>
                <Button onClick={handleClose} color="primary">
                    Cancelar
                </Button>
                <Button onClick={handleSubmit} color="primary">
                    Salvar
                </Button>
                </DialogActions>
            </Dialog>
        );
    }
}