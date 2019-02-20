import React, { Component } from 'react';
import { Card, CardContent, CardHeader, Button, TextField } from '@material-ui/core';
import "./Login.css";
import { userService } from "../../_helpers/auth-service";

class Login extends Component {
    constructor(props) {
        super(props);

        userService.logout();

        this.state = {
            username: '',
            password: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleSubmit(e) {
        e.preventDefault();

        this.setState({ submitted: true });
        const { username, password } = this.state;

        if (!(username && password)) {
            return;
        }

        this.setState({ loading: true });
        userService.login(username, password)
            .then(
                user => {
                    const { from } = this.props.location.state || { from: { pathname: "/majors" } };
                    this.props.history.push(from);
                },
                error => this.setState({ error, loading: false })
            );
    }

    render() {
        return (
            <div>
                <Card className='main-card'>
                    <CardHeader title='Login' />
                    <CardContent>
                        <TextField margin="dense" id="username" label="Usuário" type="text" onChange={this.handleChange} fullWidth />
                        <TextField margin="dense" id="password" label="Senha" type="password" onChange={this.handleChange} fullWidth/>
                        <Button color="primary" onClick={this.handleSubmit}>
                            Login
                        </Button>
                    </CardContent>
                </Card>
            </div>
        );
    }
}

export default Login;