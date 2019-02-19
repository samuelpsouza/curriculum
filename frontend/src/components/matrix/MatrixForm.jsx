import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import CloseIcon from '@material-ui/icons/Close';
import { 
        Button, Dialog,
        DialogActions, DialogContent, DialogTitle, 
        Fab, Typography, Divider, Slide,
        AppBar, Toolbar, IconButton 
    } from '@material-ui/core';

const styles = theme => ({
    fab: {
        margin: theme.spacing.unit,
    },
    extendedIcon: {
        marginRight: theme.spacing.unit,
    },
});

function Transition(props) {
    return <Slide direction="up" {...props} />;
}

class MatrixForm extends Component {
    state = {
        scroll: 'paper',
    };

    render(){
        const {openInclude, handleClose, handleSubmit, classes} = this.props;
        return (
            <Dialog
                fullScreen
                open={openInclude}
                onClose={handleClose}
                scroll={this.state.scroll}
                aria-labelledby="form-dialog-title"
                TransitionComponent={Transition}
            >
                <AppBar className={classes.appBar}>
                    <Toolbar>
                    <IconButton color="inherit" onClick={handleClose} aria-label="Close">
                        <CloseIcon />
                    </IconButton>
                    <Typography variant="h6" color="inherit" className={classes.flex}>
                        Cancelar
                    </Typography>
                    <Button color="inherit" onClick={handleSubmit}>
                        Salvar
                    </Button>
                    </Toolbar>
                </AppBar>
            </Dialog>
        );
    }
}

export default withStyles(styles)(MatrixForm);