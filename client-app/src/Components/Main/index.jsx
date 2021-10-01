import React from "react";
import styled from "styled-components";

const Main = styled.div`
    width: 70%;
`;


export default () => {
    return (
        <Main>
            <table>
                <thead>
                    <tr>
                        <th>
                            Program's name
                        </th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Computer Science</td>
                        <td>actions</td>
                    </tr>
                    <tr>
                        <td>Computer Science</td>
                        <td>actions</td>
                    </tr>
                </tbody>
            </table>
        </Main>
    );
};