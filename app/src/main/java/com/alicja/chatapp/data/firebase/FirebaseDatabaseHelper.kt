package com.alicja.chatapp.data.firebase

import android.net.Uri
import android.util.Log
import com.alicja.chatapp.delegators.Toaster
import com.alicja.chatapp.model.User
import com.alicja.chatapp.ui.registerlogin.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*


class FirebaseDatabaseHelper (private var selectedPhotoUri: Uri?, private val username: String) {

    fun uploadImageToFirebaseStorage() {
        if(selectedPhotoUri == null){
            return
        }

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")


        selectedPhotoUri.let {
            if (it != null) {
                ref.putFile(it)
                    .addOnSuccessListener {
                        Log.d(RegisterActivity.TAG, "Successfully uploaded image: ${it.metadata?.path}")

                        ref.downloadUrl.addOnSuccessListener {
                            Log.d(RegisterActivity.TAG, "File location ${it}")

                            saveUserToFirebaseDatabase(it.toString())
                        }
                    }
                    .addOnFailureListener{
                        Log.d(RegisterActivity.TAG, "Failure while uploading an image: ${it.printStackTrace()}")
                    }
            }
        }
    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String){
        val uid = FirebaseAuth.getInstance().uid?:""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User( uid, username, profileImageUrl )
        ref.setValue(user)
            .addOnSuccessListener {
                Log.d(RegisterActivity.TAG, "Successfully saved user with $uid to the database")
                
            }

            .addOnFailureListener{
                Log.d(RegisterActivity.TAG, "Error occurred while saving the user to the database: ${it.printStackTrace()}")
            }
    }
}

