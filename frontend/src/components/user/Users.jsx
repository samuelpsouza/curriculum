import React, { Component, Fragment } from 'react';
import { withStyles } from '@material-ui/core/styles';

import User from './User';
import UserForm from './UserForm';

const styles = theme => ({
    root: {
      ...theme.mixins.gutters(),
      paddingTop: theme.spacing.unit * 2,
      paddingBottom: theme.spacing.unit * 2,
    },
    button: {
      margin: theme.spacing.unit,
    },
});

class Users extends Component {
    state = {
        open: false,
        roles: [],
        users: [],
        roleSelected: {}
    }

    handleSubmit = () => {
        this.handleClose();
        const user = {}
        user.username = this.state.username
        user.password = this.state.password
        user.roleList.push(this.state.roleSelected)

        fetch(URL + '/users', {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(user)
        })
        .then(response => response.json())
        .then(response => {
            this.refresh();
        })
    }

    handleClose = () => {
        this.setState({...this.state.open, open:false})
    }

    handleChange = name => event => {
        this.setState({...this.state, [name]: event.target.value});
    }

    handleUpdate = (user) => {
        fetch(URL + '/users', {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'PUT',
            body: JSON.stringify(user)
        })
        .then(response => response.json())
        .then(response => {
            this.refresh();
        })
    }

    handleRemove = (id) => {
        fetch(URL + '/users/' + id, {
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

    refresh = (id) => {
        fetch(URL + '/users', {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'DELETE'
        })
        .then(response => response.json())
        .then(response => {
            this.setState({...this.state.users, users: response.data});
        })
    }

    componentWillMount = () => {
        this.refresh();
    }

    render(){
        return (
            <Fragment>
                {this.state.users.map(user => {
                    return (
                        <User user={user}
                            handleRemove={this.handleRemove}
                            handleUpdate={this.handleUpdate}
                        />
                    );
                })}

                <UserForm open={this.state.open} 
                    handleSubmit={this.handleSubmit} 
                    handleClose={this.handleClose}
                    handleChange={this.handleChange}
                    roles={this.state.roles}/>
            </Fragment>
        );
    }   
}

export default withStyles(styles)(Users);