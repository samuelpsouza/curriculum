import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import Major from './Major';
import MajorForm from './MajorForm';

const URL = 'http://localhost:8080';

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

class MajorList extends Component {
    constructor(props){
        super(props);

        this.state = {
            open: false,
            code:'',
            title:'',
            description: '',
            period: '',
            duration:'',
            registrationNumber:'',
            majors:[],
            courses:[]
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleRemove = this.handleRemove.bind(this);
    }

    handleClickOpen = () => {
        this.setState({ open: true });
    };
    
    handleClose = () => {
        this.setState({ open: false });
    };

    handleSubmit = () => {
        this.handleClose();
        const major = {}
        major.code = this.state.code
        major.title = this.state.title
        major.description = this.state.description
        major.period = this.state.period
        major.duration = this.state.duration
        major.registrationNumber = this.state.registrationNumber

        fetch(URL + '/majors', {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(major)
        })
        .then(response => response.json())
        .then(response => {
            this.refreshMajors();
        })

    }

    handleUpdate = (selectedMajor) => {
        fetch(URL + '/majors', {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'PUT',
            body: JSON.stringify(selectedMajor)
        })
        .then(response => response.json())
        .then(response => {
            this.refreshMajors();
        })
    }

    handleRemove = (id) => {
        fetch(URL + '/majors/' + id, {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'DELETE'
        })
        .then(response => response.json())
        .then(response => {
            this.refreshMajors();
        })
    }

    handleChange = name => event => {
        this.setState({ ...this.state, [name]: event.target.value });
    };

    refreshMajors(){
        fetch(URL + '/majors', {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(response => {
            this.setState({...this.state, majors: response.data.content});
        })
    }

    refreshCourses(){
        fetch(URL + '/courses', {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(response => {
            this.setState({...this.state, courses: response.data.content});
        })
    }

    componentWillMount = () => {
        this.refreshMajors();
    }

    componentDidMount = () => {
        this.refreshCourses();
    }

    render(){
        const { classes } = this.props;
        return (
            <div>
                <Fab color="primary" aria-label="Add" className={classes.fab} onClick={() => this.handleClickOpen()}>
                    <AddIcon />
                </Fab>
                {this.state.majors.map(major => (<Major handleSubmit={this.handleUpdate} courses={this.state.courses} key={major.id} major={major} handleRemove={this.handleRemove}/>))}
                <MajorForm 
                    open={this.state.open} 
                    code={this.state.code}
                    title={this.state.title}
                    description={this.state.description}
                    period={this.state.period}
                    duration={this.state.duration}
                    registrationNumber={this.state.registrationNumber}
                    handleSubmit={this.handleSubmit} 
                    handleClose={this.handleClose}
                    handleChange={this.handleChange}
                    />
            </div>
        );
    }
}

export default withStyles(styles)(MajorList);