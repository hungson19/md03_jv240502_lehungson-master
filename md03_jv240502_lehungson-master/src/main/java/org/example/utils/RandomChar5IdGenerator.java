package org.example.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class RandomChar5IdGenerator implements IdentifierGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int ID_LENGTH = 5;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        Random random = new Random();

        for (int i = 0; i < ID_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }
}
