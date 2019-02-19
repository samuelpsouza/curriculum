import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import CloseIcon from '@material-ui/icons/Close';
import AddIcon from '@material-ui/icons/Add';

import { 
        Button, Dialog, ListItemText,
        Typography, Slide, ListItem,
        AppBar, Toolbar, IconButton,
        List, Divider, ListItemIcon, 
        DialogContent, DialogActions,
        TextField, DialogTitle
    } from '@material-ui/core';


const URL = 'http://localhost:8080';

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
            selectedMajor: this.props.major,
            openSemesterSelection: false,
            notAssigned:[],
            semester: ''
        };
    }

    handleUpdate = (major) => {
        fetch(URL + '/majors', {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'PUT',
            body: JSON.stringify(major)
        })
        .then(response => response.json())
        .then(response => {
            console.log(response)
        })
    }

    handleOpenSelection = (course) => {
        this.setState({...this.state.openSelection, openSelection: true});
        this.setState({...this.state.selectedCourse, selectedCourse: course});
    }

    handeCloseSelection = () => {
        this.setState({...this.state.openSelection, openSelection: false});
    }

    handeCloseSemesterSelection = () => {
        this.setState({...this.state.openSemesterSelection, openSemesterSelection: false});
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

        this.handleUpdate(this.state.selectedMajor);

    }

    handleAddSemester = () => {
        this.handeCloseSemesterSelection();
        let updated = Object.create(this.state.selectedMajor)
        updated.matrix.semesterList.push({description:this.state.semester});
        this.setState({...this.state.selectedMajor, selectedMajor: updated});

        this.handleUpdate(this.state.selectedMajor);
    }

    handleOpenSemesterSelection = () => {
        this.setState({...this.state.openSemesterSelection, openSemesterSelection: true});
    }

    handleSemesterChange = name => event => {
        this.setState({ ...this.state, [name]: event.target.value });
    };

    componentDidMount = () => {
        let notAssigned = this.props.courses.filter(course => {
            if(course.semester)
                return null;
            else {
                this.props.major.matrix.courseList.forEach(element => {
                    if(element.code === course.code)
                        return null;
                });
            }
            return course;
        });

        this.setState({...this.state.notAssigned, notAssigned:notAssigned});
    }

    render(){
        const {openInclude, handleClose, classes} = this.props;

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
                    <ListItem button onClick={() => this.handleOpenSemesterSelection()}>
                        <ListItemText primary="Novo Semestre" />
                        <ListItemIcon>
                            <AddIcon />
                        </ListItemIcon>
                    </ListItem>
                    
                    {this.state.selectedMajor.matrix.semesterList.map(semester=>{
                        return (
                            <List key={semester.id}>
                                <ListItem button>
                                    <Typography variant="h6" color="inherit">
                                        {semester.description} 
                                    </Typography>
                                </ListItem>
                                <List>
                                    {this.state.selectedMajor.matrix.courseList.filter(course=>{
                                        if(course.semester===semester)
                                            return <ListItemText key={course.id} primary={course.description} />
                                        return null;
                                    })}
                                </List>
                            </List>
                            );
                    })}

                    <Divider />

                    <Typography variant="h6" color="inherit" className={classes.flex}>
                        Disciplinas Optativas
                    </Typography>
                    <ListItem button>
                        {this.state.selectedMajor.matrix.courseList.map(course=>{
                            return (<ListItemText key={course.id} primary={course.description} />);
                        })}
                    </ListItem>
                </List>

                <Divider />

                <List className={classes.root}>
                    {this.state.notAssigned.map(course => (
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
                        <DialogTitle id="form-dialog-title">Associar à Matriz</DialogTitle>
                        <DialogContent>
                            <List className={classes.root}>
                                {this.state.selectedMajor.matrix.semesterList.map(semester => (
                                    <ListItem key={semester.id} dense button onClick={() => this.handleOpenSelection()}>
                                        <ListItemText primary={semester.description} />
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
                <Dialog
                     open={this.state.openSemesterSelection}
                     aria-labelledby="form-dialog-title"
                     >  
                        <DialogTitle id="form-dialog-title">Novo Semestre</DialogTitle>
                        <DialogContent>
                            <TextField margin="dense" id="semester" label="Semestre" type="text" onChange={this.handleSemesterChange('semester')} value={this.state.semester} fullWidth/>
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={this.handeCloseSemesterSelection} color="primary">
                                Cancelar
                            </Button>
                            <Button onClick={this.handleAddSemester} color="primary">
                                Salvar
                            </Button>
                        </DialogActions>
                </Dialog>
            </Dialog>
        );
    }
}

export default withStyles(styles)(MatrixForm);