/*
 * This file is generated by jOOQ.
 */
package com.darkcom.backend.generate;


import com.darkcom.backend.generate.tables.UserInfo;
import com.darkcom.backend.generate.tables.records.UserInfoRecord;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>miniapp</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<UserInfoRecord, Long> IDENTITY_T_USER_INFO = Identities0.IDENTITY_T_USER_INFO;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<UserInfoRecord> KEY_T_USER_INFO_PRIMARY = UniqueKeys0.KEY_T_USER_INFO_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<UserInfoRecord, Long> IDENTITY_T_USER_INFO = Internal.createIdentity(UserInfo.T_USER_INFO, UserInfo.T_USER_INFO.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<UserInfoRecord> KEY_T_USER_INFO_PRIMARY = Internal.createUniqueKey(UserInfo.T_USER_INFO, "KEY_t_user_info_PRIMARY", UserInfo.T_USER_INFO.ID);
    }
}
