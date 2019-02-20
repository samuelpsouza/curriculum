import React from 'react';
import { connect } from 'react-redux';
import { Router, Route, Redirect, Switch } from 'react-router-dom'
import MajorList from './components/major/MajorList';
import CourseList from './components/course/CourseList';
import Users from './components/user/Users';
import Login from './components/user/Login';

const PrivateRoute = ({ component: Component, isAuthenticated, ...rest }) => (
    <Route {...rest} render={(props) => (
      isAuthenticated ? (<Component {...props} />) : (<Redirect to='/login' />)
    )} />
)

const AppRoutes = ({ history, isAuthenticated }) => (
    <Router history={history}>
        <Switch>
            <Route path='/' exact component={Login} />
            <Route path='/login' exact component={Login} />
            <Route path='/majors' exact component={MajorList} />

            {/* Private Routes */}

            <PrivateRoute exact path='/courses' isAuthenticated={isAuthenticated} component={CourseList}/>
            <PrivateRoute exact path='/users' isAuthenticated={isAuthenticated} component={Users} />

            <Redirect from='' to='/login' />
        </Switch>
    </Router>   
);


const mapStateToProps = state => ({
    user: state.user
})

export default connect(mapStateToProps)(AppRoutes)


