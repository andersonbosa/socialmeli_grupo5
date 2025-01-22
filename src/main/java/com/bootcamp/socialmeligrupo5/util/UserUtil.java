package com.bootcamp.socialmeligrupo5.util;

import com.bootcamp.socialmeligrupo5.dto.UserResponseDTO;
import com.bootcamp.socialmeligrupo5.exception.BadRequestException;

import java.util.Comparator;
import java.util.List;

public class UserUtil {

    private static final List<String> VALID_ORDERS = List.of("name_asc", "name_desc");

    public static List<UserResponseDTO> listUsersWithOrder(
            List<UserResponseDTO> users, String order) {

        validateOrder(order);
        Comparator<UserResponseDTO> comparator = Comparator.comparing(UserResponseDTO::userName);

        if (order.equalsIgnoreCase("name_desc")) {
            comparator = comparator.reversed();
        }

        return users.stream().sorted(comparator).toList();

    }

    private static void validateOrder(String order) {
        if (order.isBlank()) {
            throw new BadRequestException("Necessário informar o tipo de ordenação desejada!");
        }

        if (!VALID_ORDERS.contains(order.toLowerCase())) {
            throw new BadRequestException("O tipo da ordenção informada não é permitida!");
        }
    }
}
