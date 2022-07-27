#!/bin/bash

echo -e $(./gradlew run --args='--dump-asm-exec "'"$1"'"')
echo -e $(clang app/data/a.ll)

