import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import CloseIcon from '@material-ui/icons/Close';
import AddIcon from '@material-ui/icons/Add';

import { 
        Button, Dialog, ListItemText,
        Typography, Slide, ListItem,
        AppBar, Toolbar, IconButton,
        List, Divider, ListItemIcon, 
        DialogContent, DialogActions
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
    constructor(props){
        super(props);

        this.state = {
            scroll: 'paper',
            openSelection: false,
            selectedCourse: {},
            selectedMajor: this.props.major
        };
    }

    handleOpenSelection = (course) => {
        this.setState({...this.state, openSelection: true, selectedCourse: course});
    }

    handeCloseSelection = () => {
        this.setState({...this.state, openSelection: false});
    }

    handleSelection = (option) => {
        this.handeCloseSelection();
        if(option === 'optativa'){
            let updated = Object.create(this.state.selectedMajor)
            updated.matrix.courseList.push(this.state.selectedCourse);
            this.setState({...this.state.selectedMajor, selectedMajor: updated});
        }
        else{
            // Nunca modifico o estado diretamente. Sempre crio uma mutação dele
            let updatedMajor = Object.create(this.state.selectedMajor)
            updatedMajor.matrix.courseList.push(this.state.selectedCourse);

            let updatedCourse = Object.create(this.state.selectedCourse)
            updatedCourse.semester = option;
            this.setState({...this.state.selectedMajor, selectedMajor: updatedMajor});
            this.setState({...this.state.selectedCourse, selectedCourse: updatedCourse});
        }
    }

    handleSubmit = () => {
        this.props.handleClose()
        this.props.handleSubmit(this.state.selectedMajor)
    }

    render(){
        const {openInclude, handleClose, classes, courses} = this.props;

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
                    <Button color="inherit" onClick={() => this.handleSubmit()}>
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
                        {/*this.state.selectedMajor.matrix.courseList.map(course=>{
                            return (<ListItemText primary={course.description} />);
                        })*/}
                    </ListItem>
                </List>

                <Divider />

                <List className={classes.root}>
                    {courses.map(course => (
                        <ListItem key={course.id} dense button onClick={() => this.handleOpenSelection(course)}>
                            <ListItemText primary={course.id} />
                            <ListItemText primary={course.description} />
                            <ListItemIcon>
                                <AddIcon />
                            </ListItemIcon>
                        </ListItem>
                    ))}
                </List>

                <Dialog
                     open={this.state.openSelection}
                     aria-labelledby="form-dialog-title"
                     >
                        <DialogContent>
                            <List className={classes.root}>
                                {this.state.selectedMajor.matrix.semesterList.map(course => (
                                    <ListItem key={course.id} dense button onClick={() => this.handleOpenSelection()}>
                                        <ListItemText primary={course.id} />
                                        <ListItemText primary={course.description} />
                                    </ListItem>
                                ))}
                            </List>
                            <List className={classes.root}>
                                <ListItem dense button onClick={() => this.handleSelection('optativa')}>
                                    <ListItemText primary="Optativa" />
                                </ListItem>
                            </List>
                            
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={this.handeCloseSelection} color="primary">
                            Cancelar
                            </Button>
                        </DialogActions>
                </Dialog>
            </Dialog>
        );
    }
}

export default withStyles(styles)(MatrixForm);