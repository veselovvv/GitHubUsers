package com.veselovvv.githubusers.presentation.main

import com.veselovvv.githubusers.core.Communication

interface NavigationCommunication : Communication<Int> {
    class Base : Communication.Base<Int>(), NavigationCommunication
}