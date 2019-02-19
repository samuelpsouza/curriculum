import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import Course from './Course';
import CourseForm from './CourseForm';

const URL = 'http://localhost:8080/courses';

const styles = theme => ({
    fab: {
        position: 'absolute',
        bottom: theme.spacing.unit * 2,
        right: theme.spacing.unit * 2,
    },
    extendedIcon: {
        marginRight: theme.spacing.unit,
    },
  });

class CourseList extends Component {
    constructor(props){
        super(props);

        this.state = {
            open: false,
            code:'',
            description: '',
            courses:[]
        }

        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleSubmit = () => {
        this.handleClose();
        const course = {}
        course.code = this.state.code
        course.description = this.state.description
    
        fetch(URL, {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(course)
        })
        .then(response => response.json())
        .then(response => {
            this.refresh();
        })
    }

    handleChange = name => event => {
        this.setState({ ...this.state, [name]: event.target.value });
    };

    handleClickOpen = () => {
        this.setState({ open: true });
    };
    
    handleClose = () => {
        this.setState({ open: false });
    };

    refresh(){
        fetch(URL, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(response => {
            this.setState({...this.state, courses: response.data.content});
        })
    }

    handleRemove = (id) => {
        fetch(URL + '/' + id, {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'DELETE'
        })
        .then(response => response.json())
        .then(response => {
            this.refresh();
        })
    }

    componentWillMount(){
       this.refresh();
    }

    render(){
        const {classes} = this.props;
        return (
            <div>
                <Fab color="primary" aria-label="Add" className={classes.fab} onClick={() => this.handleClickOpen()}>
                    <AddIcon />
                </Fab>
                {this.state.courses.map(course => (<Course key={course.id} course={course} handleRemove={this.handleRemove} />))}
                <CourseForm 
                    open={this.state.open} 
                    code={this.state.code}
                    description={this.state.description}
                    handleSubmit={this.handleSubmit} 
                    handleClose={this.handleClose}
                    handleChange={this.handleChange}
                />
            </div>
        );
    }
}

export default withStyles(styles)(CourseList);