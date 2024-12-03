use crate::day3::Instruction::Mul;

pub fn day3() {
    let part1_result = day3_part1(INPUT);
    println!("Day 3, Part 1: {}", part1_result);
    let part2_result = day3_part2(INPUT);
    println!("Day 3, Part 2: {}", part2_result);
}

fn day3_part1(input: &str) -> i32 {
    parse_input(input).iter().map(|(a, b)| a * b).sum()
}

fn day3_part2(input: &str) -> i32 {
    parse_input_conditional(input)
        .iter()
        .fold((0, true), |acc, x| match x {
            Mul(a, b) => {
                if acc.1 {
                    (acc.0 + a * b, acc.1)
                } else {
                    acc
                }
            }
            Instruction::Do => (acc.0, true),
            Instruction::Dont => (acc.0, false),
        })
        .0
}

fn parse_input(input: &str) -> Vec<(i32, i32)> {
    let r = regex::Regex::new(r"mul\(\d+,\d+\)").unwrap();
    r.find_iter(input)
        .map(|m| m.as_str().split(","))
        .map(|mut x| {
            let a = x
                .next()
                .unwrap()
                .strip_prefix("mul(")
                .unwrap()
                .parse()
                .unwrap();
            let b = x
                .next()
                .unwrap()
                .strip_suffix(")")
                .unwrap()
                .parse()
                .unwrap();
            (a, b)
        })
        .collect()
}

fn parse_input_conditional(input: &str) -> Vec<Instruction> {
    let r = regex::Regex::new(
        r#"(?x)
            (mul\(\d+,\d+\)) |
            (do\(\)) |
            (don't\(\))
            "#,
    )
    .unwrap();

    r.find_iter(input)
        .map(|m| m.as_str())
        .map(|x| {
            if x.starts_with("mul") {
                let mut x = x.split(",");
                let a = x
                    .next()
                    .unwrap()
                    .strip_prefix("mul(")
                    .unwrap()
                    .parse()
                    .unwrap();
                let b = x
                    .next()
                    .unwrap()
                    .strip_suffix(")")
                    .unwrap()
                    .parse()
                    .unwrap();
                Mul(a, b)
            } else {
                match x {
                    "don't()" => Instruction::Dont,
                    "do()" => Instruction::Do,
                    &_ => panic!("Regex parsing failed."),
                }
            }
        })
        .collect()
}

enum Instruction {
    Mul(i32, i32),
    Do,
    Dont,
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_day3_part1() {
        let test_input: &str = include_str!("test_input.txt");
        assert_eq!(day3_part1(test_input), 161);
    }

    #[test]
    fn test_day3_part2() {
        let test_input: &str = include_str!("test_input_part2.txt");
        assert_eq!(day3_part2(test_input), 48);
    }
}

const INPUT: &str = include_str!("input.txt");
