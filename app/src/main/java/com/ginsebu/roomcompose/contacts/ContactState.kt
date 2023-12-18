package com.ginsebu.roomcompose.contacts

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME,
    val contact: Contact? = null,
    val isDeleteContact: Boolean = false,
)
