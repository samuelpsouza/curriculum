import React, { Component } from 'react';
import { bindActionCreators } from 'redux';
import { reduxForm, Field } from 'redux-form';
import { connect } from 'react-redux';
import requestLogin from './actions';
import required from '../../_helpers/required';

import { Card, CardContent, CardHeader, CircularProgress,
    Button, TextField } from '@material-ui/core';
import "./Login.css";

class Login extends Component {
    constructor(props) {
        super(props)
        this.onSubmit = this.onSubmit.bind(this)
    }

    onSubmit = values => {
        console.log(values)
        //const { requestLogin } = this.props;
        //requestLogin(values)
    }

    render() {
        const { handleSubmit } = this.props
        return (
            <div>
                <Card className='main-card'>
                    <CardHeader title='Login' />
                    <CardContent>
                        <form onSubmit={handleSubmit(val => this.onSubmit(val))}>
                            <Field label='Usuário' name='username' component={TextField} type='text'
                                    validate={[required]} fullWidth/>
                            <Field label='Password' name='password' component={TextField} type='password'
                                    fullWidth margin='normal' validate={[required]} />

                            <Button color='primary' type="submit" variant='contained' 
                                style={{ marginTop: 10 }} fullWidth>
                                Login
                            </Button>
                        </form>
                    </CardContent>
                </Card>
            </div>
        );
    }
}

const LoginForm = reduxForm({ form: 'loginForm' })(Login);
const mapDispatchToProps = dispatch => bindActionCreators({ requestLogin },
    dispatch)
export default connect(null, mapDispatchToProps)(LoginForm)