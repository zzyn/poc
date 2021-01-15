import { ApiProperty } from "@nestjs/swagger";

class ApiErrorDto {
  @ApiProperty({
    nullable: false,
    required: true,
  })
  method: string;

  @ApiProperty({
    nullable: false,
    required: true,
  })
  path: string;

  @ApiProperty({
    nullable: false,
    required: false,
  })
  body: string;

  @ApiProperty({
    nullable: false,
    required: true,
  })
  timestamp: Date;

  @ApiProperty({
    nullable: false,
    required: true,
  })
  status: number;

  @ApiProperty({
    nullable: false,
    required: true,
  })
  message: string;

  @ApiProperty({
    nullable: false,
    required: false,
  })
  stack: string;
}

export { ApiErrorDto };
