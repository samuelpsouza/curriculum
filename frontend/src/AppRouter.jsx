import React from 'react';
import { HashRouter, Route, Redirect, Switch } from 'react-router-dom'
import MajorList from './components/major/MajorList';
import CourseList from './components/course/CourseList';

const AppRoutes = props => (
    <HashRouter>
        <Switch>
            <Route path='/' exact component={MajorList} />
            <Route path='/courses' exact component={CourseList} />
            <Route path='/users' component={null} />
            <Redirect from='' to='/' />
        </Switch>
    </HashRouter>   
);

export default AppRoutes;