package com.school.schedule.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

@Schema(name = "Lookup establishment section class response")
public record LookupEstablishmentSectionClassResponse(boolean success,
                                                      EstablishmentSectionClassResponse establishmentSectionClassResponse) implements Serializable {
}
