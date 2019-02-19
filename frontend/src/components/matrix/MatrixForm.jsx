import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import CloseIcon from '@material-ui/icons/Close';
import AddIcon from '@material-ui/icons/Add';
import { 
        Button, Dialog, ListItemText,
        Typography, Slide, ListItem,
        AppBar, Toolbar, IconButton,
        List, Divider, ListItemIcon,
        Checkbox 
    } from '@material-ui/core';

const styles = theme => ({
    fab: {
        margin: theme.spacing.unit,
    },
    extendedIcon: {
        marginRight: theme.spacing.unit,
    },
    appBar: {
        position: 'relative',
      },
    flex: {
        flex: 1,
    },
});

function Transition(props) {
    return <Slide direction="up" {...props} />;
}

class MatrixForm extends Component {
    state = {
        scroll: 'paper',
        courses: []
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
                <List>
                    <Typography variant="h6" color="inherit" className={classes.flex}>
                        Disciplinas Obrigatórias
                    </Typography>
                    <ListItem button>
                        <ListItemText primary="Semestre" />
                        <ListItemIcon>
                            <AddIcon />
                        </ListItemIcon>
                    </ListItem>
                    <Divider />

                    <Typography variant="h6" color="inherit" className={classes.flex}>
                        Disciplinas Optativas
                    </Typography>
                    <ListItem button>
                        <ListItemText primary="Disciplina" />
                    </ListItem>
                </List>

                <Divider />

                <List className={classes.root}>
                    {this.state.courses.map(value => (
                    <ListItem key={value} role={undefined} dense button onClick={this.handleToggle(value)}>
                        <Checkbox
                            checked={this.state.checked.indexOf(value) !== -1}
                            tabIndex={-1}
                            disableRipple
                        />
                        <ListItemText primary={`Line item ${value + 1}`} />
                    </ListItem>
                    ))}
                </List>
            </Dialog>
        );
    }
}

export default withStyles(styles)(MatrixForm);