package com.iscoding.myinstegramclone.domain.navigation

sealed class Screens(val route: String) {
    object SplashScreen: Screens("splash_screen")
    object LogInScreen: Screens("login_screen")
    object SignUpScreen: Screens("signup_screen")
    object FeedsScreen: Screens("feed_screen")
    object SearchScreen: Screens("search_screen")
    object PickImageScreen: Screens("pick_image_screen")
    object SearchDetailsScreen: Screens("search_details_screen")
    object ProfileScreen: Screens("profile_screen")
    object ChatDetailsScreen: Screens("chat_details_screen")
    object ChatListsScreen: Screens("chat_list_screen")
    object NotificationsScreen: Screens("notifications_screen")
    object EditEmailScreen: Screens("edit_email_screen")
    object EditPasswordScreen: Screens("edit_password_screen")


}
