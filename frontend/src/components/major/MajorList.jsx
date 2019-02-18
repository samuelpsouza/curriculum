import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import Major from './Major';
import MajorForm from './MajorForm';

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

class MajorList extends Component {
    state = {
        open: false,
        major: {},
        majors:[]
    }
   
    handleClickOpen = () => {
        this.setState({ open: true });
    };
    
    handleClose = () => {
        this.setState({ open: false });
    };

    handleSubmit = () => {
        console.log()
    }
    componentWillMount(){
        fetch(URL, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(response => {
            this.setState({...this.state, majors: response.data.content});
        })
    }

    render(){
        const { classes } = this.props;
        return (
            <div>
                <Fab color="primary" aria-label="Add" className={classes.fab} onClick={() => this.handleClickOpen()}>
                    <AddIcon />
                </Fab>
                {this.state.majors.map(major => (<Major major={major} />))}
                <MajorForm open={this.state.open} major={this.state.major} handleSubmit={this.handleSubmit} handleClose={this.handleClose}/>
            </div>
        );
    }
}

export default withStyles(styles)(MajorList);