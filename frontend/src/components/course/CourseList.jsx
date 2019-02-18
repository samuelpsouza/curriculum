import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import Course from './Course';

const URL = 'http://localhost:8080/majors';

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
            courses:[]
        }
    }

    render(){
        const {classes} = this.props;
        return (
            <div>
                <Fab color="primary" aria-label="Add" className={classes.fab} onClick={() => this.handleClickOpen()}>
                    <AddIcon />
                </Fab>
                {this.state.courses.map(course => (<Course key={course.id} course={course} />))}
            </div>
        );
    }
}

export default withStyles(styles)(CourseList);