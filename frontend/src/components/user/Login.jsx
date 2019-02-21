import React, { Component } from 'react';
import { bindActionCreators } from 'redux';
import { reduxForm, Field } from 'redux-form';
import { connect } from 'react-redux';
import { loginUser } from './actions';

import { Card, CardContent, CardHeader,
    Button, TextField } from '@material-ui/core';
import "./Login.css";
class Login extends Component {
    constructor(props) {
        super(props)
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit = values => {
        const { loginUser } = this.props;
        loginUser(values);
    }

    render() {
        const { handleSubmit } = this.props;
        return (
            <Card className='main-card'>
                <CardHeader title='Login' />
                <CardContent>
                    <form onSubmit={handleSubmit(this.onSubmit)}>
                        <Field label='Usuário' id="username" name='username' 
                            component={TextField} type='text' fullWidth required/>
                        <Field label='Password' id="password" name='password'
                            component={TextField} type='password' fullWidth required/>

                        <Button color='primary' type="submit" variant='contained' 
                            style={{ marginTop: 10 }} fullWidth>
                            Login
                        </Button>
                    </form>
                </CardContent>
            </Card>
        );
    }
}

const LoginForm = reduxForm({ form: 'loginForm' })(Login);
const mapDispatchToProps = dispatch => bindActionCreators({ loginUser },
    dispatch);
export default connect(null, mapDispatchToProps)(LoginForm);